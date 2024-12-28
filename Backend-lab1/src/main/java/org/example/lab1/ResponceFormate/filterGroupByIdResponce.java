package org.IS.lab1.ResponceFormate;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class filterGroupByIdResponce {
    Long id;
    Long count;
}
