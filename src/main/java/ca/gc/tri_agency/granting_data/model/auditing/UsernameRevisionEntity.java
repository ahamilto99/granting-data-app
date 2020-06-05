package ca.gc.tri_agency.granting_data.model.auditing;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity(UsernameRevisionEntityListener.class)
@Table(name = "revision_info")
public class UsernameRevisionEntity {

	@Id
	@RevisionNumber
	@SequenceGenerator(name = "SEQ_REVISION_INFO", sequenceName = "SEQ_REVISION_INFO", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_REVISION_INFO")
	private Long revision;

	@RevisionTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "revtstmp")
	private Date revTimestamp;
	
	private String username;

	public Long getRevision() {
		return revision;
	}

	public void setRevision(Long revision) {
		this.revision = revision;
	}

	public Date getRevTimestamp() {
		return revTimestamp;
	}

	public void setRevTimestamp(Date revTimestamp) {
		this.revTimestamp = revTimestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsernameRevisionEntity [revision=");
		builder.append(revision);
		builder.append(", revTimestamp=");
		builder.append(revTimestamp);
		builder.append(", username=");
		builder.append(username);
		builder.append("]");
		return builder.toString();
	}
	
}
