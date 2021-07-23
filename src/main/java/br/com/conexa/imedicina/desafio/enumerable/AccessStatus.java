package br.com.conexa.imedicina.desafio.enumerable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum AccessStatus {
    ONLINE(0),
    OFFLINE(1);

    @Getter
    private Integer id;

    public static AccessStatus fromId(Integer id) {
        for (int i = 0; i < AccessStatus.values().length; i++) {
            if (AccessStatus.values()[i].id.equals(id)) {
                return AccessStatus.values()[i];
            }
        }
        return null;
    }
}
