package com.devops.bidder.web;

import com.devops.bidder.command.AddBidCommand;
import com.devops.bidder.command.DeleteBidCommand;
import com.devops.bidder.domain.Bid;
import com.devops.bidder.service.BidService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BidRestController.class)
@AutoConfigureMockMvc
@Import(RestExceptionHandler.class)
class BidRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AddBidCommand addBidCommand;

    @MockBean
    private DeleteBidCommand deleteBidCommand;

    @MockBean
    private BidService bidService;

    @Test
    void addBidReturnsCreated() throws Exception {
        Bid saved = new Bid();
        saved.setId("bid-1");
        saved.setItemId("item-1");
        saved.setAmount(new BigDecimal("12.50"));
        saved.setBidder("alice");
        saved.setCreatedAt(Instant.parse("2026-04-14T12:00:00Z"));
        when(addBidCommand.execute(eq("item-1"), any())).thenReturn(saved);

        String body = "{\"amount\":12.50,\"bidder\":\"alice\"}";
        mockMvc.perform(post("/api/items/item-1/bids")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("bid-1"))
                .andExpect(jsonPath("$.bidder").value("alice"));

        verify(addBidCommand).execute(eq("item-1"), any());
    }

    @Test
    void addBidValidationFails() throws Exception {
        String body = "{\"amount\":-1,\"bidder\":\"\"}";
        mockMvc.perform(post("/api/items/item-1/bids")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());

    }

    @Test
    void getBidsReturnsList() throws Exception {
        when(bidService.getRemoteBids("item-1")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/items/item-1/bids"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(bidService).getRemoteBids("item-1");
    }
}
