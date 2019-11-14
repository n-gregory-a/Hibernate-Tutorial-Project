package entity;

import javax.persistence.*;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    // annotate the class as an entity and map to db table

    // define the fields
    // annotate the fields with db column names
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youTubeChannel;

    @Column(name = "hobby")
    private String hobby;

    // add new field for instructor
    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    private Instructor instructor;

    // create constructors

    public InstructorDetail() {
    }

    public InstructorDetail(String youTubeChannel, String hobby) {
        this.youTubeChannel = youTubeChannel;
        this.hobby = hobby;
    }

    // generate getter/setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYouTubeChannel() {
        return youTubeChannel;
    }

    public void setYouTubeChannel(String youTubeChannel) {
        this.youTubeChannel = youTubeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    // generate toString() method
    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youTubeChannel='" + youTubeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
