package oncall.dto;

import java.util.List;
import oncall.enums.DutyType;

public record EmergencyDutyDTO(DutyType dutyType, List<String> nicknames) {
}
