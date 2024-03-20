package cat.proven.leisure.model;

/**
 *
 * @author Jose Moreno
 */
public class ActivityUser {
    private long activityId;
    private long userId;

    public ActivityUser(long activityId, long userId) {
        this.activityId = activityId;
        this.userId = userId;
    }

    public ActivityUser() {
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.activityId ^ (this.activityId >>> 32));
        hash = 29 * hash + (int) (this.userId ^ (this.userId >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ActivityUser other = (ActivityUser) obj;
        if (this.activityId != other.activityId) {
            return false;
        }
        return this.userId == other.userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ActivityUser{");
        sb.append("activityId=").append(activityId);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
    
    
}
