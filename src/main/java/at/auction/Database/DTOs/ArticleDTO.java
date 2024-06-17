package at.auction.Database.DTOs;

import at.auction.Database.DisplayableCard;

import java.sql.Date;

public record ArticleDTO(int articleId, int userId, String label, String description, Date startTime, Date endTime, byte free, double currentPrice, String cancellationState) implements DisplayableCard {

}
