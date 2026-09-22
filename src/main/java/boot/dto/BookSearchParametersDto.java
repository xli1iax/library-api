package boot.dto;

public record BookSearchParametersDto(String title,
                                      String[] authors,
                                      String[] prices){
}
