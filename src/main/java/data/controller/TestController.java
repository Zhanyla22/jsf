package data.controller;

import data.dto.request.AddDelayDto;
import data.dto.response.DelayUserDto;
import data.service.AttendRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
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
public class TestController {

//    public String data;
//
//    public String getData() {
//        return data;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }
//
//    public String greet() {
//        return "hello";
//    }

    private final AttendRecordService attendRecordService;

    @PostMapping("/add")
    public void add(@RequestBody AddDelayDto addDelayDto) {
        attendRecordService.addDelayMin(addDelayDto);
    }

    @GetMapping("/{id}")
    public List<DelayUserDto> getAllByUser(@PathVariable Long id) {
        return attendRecordService.getAllByUser(id);
    }
}
