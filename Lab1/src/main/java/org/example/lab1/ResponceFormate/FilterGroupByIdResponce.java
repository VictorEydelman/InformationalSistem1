package org.example.lab1.ResponceFormate;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilterGroupByIdResponce {
    Long id;
    Long count;
}
