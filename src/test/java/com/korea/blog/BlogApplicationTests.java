package com.korea.blog;

import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.note.repository.NoteRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	private NoteRepository noteRepository;

	@Test
	@DisplayName("노트 수정")
	@Rollback(false)
	@Transactional // 영속성 컨텍스트
	void t2() {
		Note note = noteRepository.findById(6L).get();
		
		note.setTitle("제목 수정2");
		note.setContent("내용 수정2");

	}

	@Test
	@DisplayName("노트 테스트 데이터 생성")
	void t1() {

		Note note1 = Note.builder()
				.title("title1")
				.content("content1")
				.build();

		noteRepository.save(note1);

	}

}
