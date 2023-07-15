package kz.bit.kormefinall.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private String email;
    private String title;
    private String avatar;
    private boolean isActive;

    public String getAvatar() {
        if (avatar == null) {
            return "/defaults/default-user.png";
        }
        return avatar;
    }
}