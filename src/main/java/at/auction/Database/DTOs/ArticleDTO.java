package at.auction.Database.DTOs;

import at.auction.Database.DisplayableCard;

public record ArticleDTO(int articleId, int userId, String label, String description, String startTime, String endTime, byte free, double startPrice, String cancellationState) implements DisplayableCard {

}
