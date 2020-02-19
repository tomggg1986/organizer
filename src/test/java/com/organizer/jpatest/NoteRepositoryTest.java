package com.organizer.jpatest;

import com.organizer.jpa.NoteRepository;
import com.organizer.model.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void getAllTest(){
        List<Note> all = noteRepository.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    public void getNoteByTaskID(){
        List<Note> noteByTask = noteRepository.findNoteByTaskId(33333333l);
        assertThat(noteByTask.stream().filter(e -> e.getName().equals("pranie")).findFirst()).isNotEmpty();
    }
}
