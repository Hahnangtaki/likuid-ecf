package id.tech.cakra.likuidecf.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.MemberAccount} entity.
 */
public class MemberAccountDTO implements Serializable {

    private Long id;

    private String memberEmail;

    private String phoneNumber;

    private LocalDate memberSince;

    private Integer memberStatus;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MemberAccountDTO memberAccountDTO = (MemberAccountDTO) o;
        if (memberAccountDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), memberAccountDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MemberAccountDTO{" +
            "id=" + getId() +
            ", memberEmail='" + getMemberEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", memberSince='" + getMemberSince() + "'" +
            ", memberStatus=" + getMemberStatus() +
            "}";
    }
}
