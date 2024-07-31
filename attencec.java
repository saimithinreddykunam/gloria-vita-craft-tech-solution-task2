
import com.example.employeemanagement.model.Attendance;
import com.example.employeemanagement.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @PostMapping
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @GetMapping("/{id}")
    public Attendance getAttendanceById(@PathVariable Long id) {
        return attendanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attendance not found"));
    }

    @PutMapping("/{id}")
    public Attendance updateAttendance(@PathVariable Long id, @RequestBody Attendance attendanceDetails) {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attendance not found"));
        attendance.setDate(attendanceDetails.getDate());
        attendance.setStatus(attendanceDetails.getStatus());
        return attendanceRepository.save(attendance);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        Attendance attendance = attendanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Attendance not found"));
        attendanceRepository.delete(attendance);
    }
}
