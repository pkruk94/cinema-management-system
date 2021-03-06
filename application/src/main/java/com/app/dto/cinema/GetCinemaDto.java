package com.app.dto.cinema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCinemaDto {

    private Long id;
    private String name;
    private String city;
    private String addressLine;
    private List<Integer> roomNumbers;
}
