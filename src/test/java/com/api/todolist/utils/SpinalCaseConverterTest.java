package com.api.todolist.utils;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import com.api.todolist.util.SpinalCaseConverter;

public class SpinalCaseConverterTest {
	final String[] words = new String[] {"CarlosHenriqueMelo","carlosHenrique-Melo", "carlos Henrique Melo", "carlos_Henrique_Melo",
		"Carlos Henrique_Melo", " Carlos_HenriqueMelo ", " Carlos_HenriqueMELO "};
	
	
	@Test
	public void spinalCaseConvertionsTest() {
		String wordInSpinalCase = "carlos-henrique-melo";
		Arrays.asList(words).forEach(word ->{
			Assertions.assertThat(SpinalCaseConverter.toSpinalCase(word)).isEqualTo(wordInSpinalCase);
		});
	}
}
