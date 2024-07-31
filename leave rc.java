
import com.example.employeemanagement.model.LeaveRequest;
import com.example.employeemanagement.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaverequests")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @GetMapping
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    @PostMapping
    public LeaveRequest createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    @GetMapping("/{id}")
    public LeaveRequest getLeaveRequestById(@PathVariable Long id) {
        return leaveRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave Request not found"));
    }

    @PutMapping("/{id}")
    public LeaveRequest updateLeaveRequest(@PathVariable Long id, @RequestBody LeaveRequest leaveRequestDetails) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave Request not found"));
        leaveRequest.setStartDate(leaveRequestDetails.getStartDate());
        leaveRequest.setEndDate(leaveRequestDetails.getEndDate());
        leaveRequest.setStatus(leaveRequestDetails.getStatus());
        leaveRequest.setReason(leaveRequestDetails.getReason());
        return leaveRequestRepository.save(leaveRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteLeaveRequest(@PathVariable Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave Request not found"));
        leaveRequestRepository.delete(leaveRequest);
    }
}
