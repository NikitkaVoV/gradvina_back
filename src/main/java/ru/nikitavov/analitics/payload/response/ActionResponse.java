package ru.nikitavov.analitics.payload.response;

import ru.nikitavov.analitics.message.ActionResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionResponse {
    private Long id;
    private String code;
    private String field;

    public ActionResponse(ActionResult action) {
        id = action.getId();
        code = action.getCode();
        field = action.getField();
    }

    public static ActionResponse create(ActionResult action) {
        return new ActionResponse(action);
    }
}
