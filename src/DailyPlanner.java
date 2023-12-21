import java.util.*;

class DailyPlanner {
    private Map<Day, List<String>> dayActivities;

    public DailyPlanner() {
        dayActivities = new HashMap<>();
        for (Day day : Day.values()) {
            dayActivities.put(day, new ArrayList<>());
        }
    }

    public void addActivity(Day day, String activity) {
        if (activity == null) {
            throw new NoActivityException("Activity cannot be null.");
        }
        dayActivities.get(day).add(activity);
    }

    public void removeActivity(Day day, String activity) {
        if (!dayActivities.get(day).remove(activity)) {
            throw new NoActivityException("Activity not found for " + day + ".");
        }
    }

    public List<String> getActivities(Day day) {
        return dayActivities.get(day);
    }

    public Map<Day, List<String>> endPlanning() throws NoActivitiesForDayException {
        Map<Day, List<String>> result = new HashMap<>();

        for (Map.Entry<Day, List<String>> entry : dayActivities.entrySet()) {
            List<String> activities = entry.getValue();
            if (activities.isEmpty()) {
                throw new NoActivitiesForDayException("No activities for " + entry.getKey() + ".");
            }
            result.put(entry.getKey(), new ArrayList<>(activities));
        }

        return result;
    }

    public static void main(String[] args) {
        DailyPlanner dailyPlanner = new DailyPlanner();

        try {
            dailyPlanner.addActivity(Day.MONDAY, "Work");
            dailyPlanner.addActivity(Day.MONDAY, "Gym");

            dailyPlanner.addActivity(Day.TUESDAY, "Study");

            dailyPlanner.removeActivity(Day.MONDAY, "Gym");

            dailyPlanner.addActivity(Day.WEDNESDAY, "Meeting");
            dailyPlanner.addActivity(Day.WEDNESDAY, "Lunch");
            dailyPlanner.addActivity(Day.SUNDAY, "Walk");
            dailyPlanner.addActivity(Day.THURSDAY, "Work");
            dailyPlanner.addActivity(Day.FRIDAY, "Trip");
            dailyPlanner.addActivity(Day.SATURDAY, "Dinner");


            Map<Day, List<String>> plan = dailyPlanner.endPlanning();
            System.out.println("Daily Planner:");
            for (Map.Entry<Day, List<String>> entry : plan.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (NoActivitiesForDayException | NoActivityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}