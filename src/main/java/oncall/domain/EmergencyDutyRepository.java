package oncall.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmergencyDutyRepository {

    private static final EmergencyDutyRepository instance = new EmergencyDutyRepository();
    private List<EmergencyDuty> assignedDuties;

    private EmergencyDutyRepository() {
        assignedDuties = new ArrayList<>();
    }

    public static EmergencyDutyRepository getInstance() {
        return instance;
    }

    public List<EmergencyDuty> getAssignedDuties() {
        return Collections.unmodifiableList(assignedDuties);
    }

    public void addDuty(EmergencyDuty duty) {
        assignedDuties.add(duty);
    }

    public void clearDuties() {
        assignedDuties.clear();
    }
}
