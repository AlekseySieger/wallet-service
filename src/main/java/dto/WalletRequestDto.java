package dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
public class WalletRequestDto {
    private UUID walletId;
    private String operationType;
    private long amount;


}
