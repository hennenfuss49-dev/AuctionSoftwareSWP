package at.auction.Database.DTOs;

import java.sql.Timestamp;

public record BidDTO(int bidId, int userID, int articleID, double amount, Timestamp timestamp) { }
