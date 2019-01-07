package com.samuylov.projectstart;

import com.samuylov.projectstart.enumeration.SortType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ProjectStartApplication {

	@Bean
	public Map<SortType, Sort> getSortMap() {
		Map<SortType, Sort> sortMap = new HashMap<>();
		sortMap.put(SortType.RATING_ASC, new Sort(Sort.Direction.ASC, "rating"));
		sortMap.put(SortType.RATING_DESC, new Sort(Sort.Direction.DESC, "rating"));
		return sortMap;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectStartApplication.class, args);
	}
}

