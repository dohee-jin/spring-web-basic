package com.spring.basic.practice;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/practice/notes")
public class NoteController {

    Map<Long, Note> notePad = new HashMap<>();
    private Long currentId = 1L;

    public NoteController() {
        notePad.put(currentId, new Note(currentId,"TIL 작성"));
        currentId++;
    }

    // 노트 추가
    @PostMapping
    public String write(String content) {

        Note note = new Note(currentId++, content);
        notePad.put(note.getId(), note);
        return "노트가 추가되었습니다. ID: " + note.getId();
    }

    // 전체 노트 조회
    @GetMapping
    // note 객체 반환
   /* public List<Note> list() {
        List<Note> noteList = new ArrayList<>();
        for(Long key : notePad.keySet()) {
            noteList.add(notePad.get(key));
        }
        return noteList;
    }*/

    // String 으로 반환
    public List<String> list() {
        if(notePad.isEmpty()) {
            return List.of("등록된 노트가 없습니다.");
        }
        List<String> noteList = new ArrayList<>();
        for(Note note : notePad.values()){
            noteList.add("ID: " + note.getId() + ", " + note.getContent());
        }
        return noteList;
    }

    // 개별 노트 조회
    @GetMapping("/{id}")
    public String read(@PathVariable Long id) {
        Note note = notePad.get(id);
        if(note == null) {
            return "해당 ID의 노트가 존재하지 않습니다.";
        }
        return "ID : " + note.getId() +" , 내용: " + note.getContent();
    }

    // 노트 수정
    @PutMapping("/{id}")
    public String update(@RequestParam String content, @PathVariable Long id) {
        Note note = notePad.get(id);
        if(note == null) {
            return "해당 ID의 노트가 존재하지 않습니다.";
        }
        note.setContent(content);

        return "ID: " + note.getId() + "의 내용이 수정되었습니다.";
    }

    // 노트 삭제
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        Note note = notePad.remove(id);
        if(note == null) {
            return "해당 ID의 노트가 존재하지 않습니다.";
        }

        return "노트 ID:" + id + "가 삭제되었습니다.";
    }
}
