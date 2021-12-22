package com.aminabla.wallet.infra.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletEntityId implements Serializable {

    @Column(name = "alias")
    private String alias;

    @Column(name = "userId")
    private String userId;

}
