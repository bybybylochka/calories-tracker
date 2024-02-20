package by.bsuir.caloriestracker.controller;

import by.bsuir.caloriestracker.dto.EditorDto;
import by.bsuir.caloriestracker.models.Editor;
import by.bsuir.caloriestracker.request.EditorRequest;
import by.bsuir.caloriestracker.response.EditorResponse;
import by.bsuir.caloriestracker.service.EditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/editor")
public class EditorController {
    private final EditorService editorService;

    @PostMapping("/add")
    public EditorDto addEditor(@RequestBody EditorRequest request){
        return editorService.addEditor(request);
    }

    @GetMapping("")
    public EditorResponse getAllEditors(){
        return editorService.findAll();
    }
}
