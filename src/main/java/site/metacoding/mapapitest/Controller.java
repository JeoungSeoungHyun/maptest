package site.metacoding.mapapitest;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class Controller {

    private final Repository repository;

    // 데이터 저장 메서드
    @GetMapping("/download")
    public String down() {

        RestTemplate rt = new RestTemplate();

        DownloadDto[] response = rt.getForObject(
                "http://pettravel.kr/api/listPart.do?page=1&pageBlock=133&partCode=PC01",
                DownloadDto[].class);

        List<DownloadDto> dto = Arrays.asList(response);

        List<Item> list = dto.get(0).getResultList();

        repository.saveAll(list);

        System.out.println(list);

        return "성공";

    }
}
