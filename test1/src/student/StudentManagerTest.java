package student;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)  
@TestMethodOrder(OrderAnnotation.class)
class StudentManagerTest {

    private StudentManager manager;
    private String[] names;
    private String otherName;

    @BeforeAll
    void setUpAll() {
        manager = new StudentManager();

        names = new String[]{
                "홍길동", "김철수", "이영희", "박민수", "최지우",
                "Emily", "Tom", "Alice", "Sophia", "Daniel"
        };
        otherName = "아무거나";

        for (String name : names) {
            manager.addStudent(name);
        }
    }

    @Test
    @Order(1)

    void testHasAllStudents() {
        for (String name : names) {
            assertTrue(manager.hasStudent(name), name + " 학생이 존재해야 합니다.");
        }
    }

    @Test
    @Order(2)
    void testAddDuplicateStudentException() {
        String duplicateName = names[0];

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            manager.addStudent(duplicateName);
                        });

        assertEquals(
                "이미 존재하는 학생입니다: " + duplicateName,
                exception.getMessage()
        );
    }

    @Test
    @Order(3)
    void testRemoveNonExistentStudentException() {
 
        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            manager.removeStudent(otherName);
                        });

        assertEquals(
                "존재하지 않는 학생입니다: " + otherName,
                exception.getMessage()
        );
    }

    @Test
    @Order(4)
    void testRemoveAllStudents() {
        for (String name : names) {
            manager.removeStudent(name);
            assertFalse(manager.hasStudent(name), name + " 학생이 제거되지 않았습니다.");
        }
    }
}