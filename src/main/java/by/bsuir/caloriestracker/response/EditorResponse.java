package by.bsuir.caloriestracker.response;

import by.bsuir.caloriestracker.dto.EditorDto;
import by.bsuir.caloriestracker.models.Editor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EditorResponse {
    private List<EditorDto> editors;
}
