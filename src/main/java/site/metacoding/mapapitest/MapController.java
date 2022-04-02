package site.metacoding.mapapitest;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MapController {

    private final Repository repository;

    // 데이터 저장 메서드
    @GetMapping("/")
    public String download() {

        RestTemplate rt = new RestTemplate();

        DownloadDto[] response = rt.getForObject(
                "http://pettravel.kr/api/listPart.do?page=1&pageBlock=133&partCode=PC01",
                DownloadDto[].class);

        List<DownloadDto> dto = Arrays.asList(response);

        List<Item> list = dto.get(0).getResultList();

        repository.saveAll(list);

        return "/home";
    }

    @GetMapping("/loading")
    public @ResponseBody List<List<String>> load() {

        List<Item> list = repository.findAll();
        PointDto pointDto = new PointDto();

        List<List<String>> points = pointDto.toPoint(list);

        return points;

    }
}
