package data.controller;

import data.controller.base.BaseController;
import data.dto.request.AddDelayDto;
import data.dto.response.AddAttendResponse;
import data.dto.response.DelayUserDto;
import data.dto.response.ResponseDto;
import data.service.AttendRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.faces.bean.ManagedBean;
import java.util.List;


@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@ManagedBean(name = "auth")
@Scope
@Controller
public class TestController extends BaseController {

    private final AttendRecordService attendRecordService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> add(@RequestBody AddDelayDto addDelayDto) {
        AddAttendResponse attens = attendRecordService.addDelayMin(addDelayDto);
        return constructSuccessResponse(attens);
    }

    @GetMapping("/{id}")
    public List<DelayUserDto> getAllByUser(@PathVariable Long id) {
        return attendRecordService.getAllByUser(id);
    }
}
