package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.application.services.StudentsService;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.*;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.GradeType;
import com.highwaytoheaven.estudentsbookapi.infrastructure.mappers.*;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.*;
import com.highwaytoheaven.estudentsbookapi.infrastructure.services.converters.GradeConverter;
import com.highwaytoheaven.model.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class StudentsServiceImpl extends BasicUsersService implements StudentsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final StudentMapper studentMapper;
    private final ContactDetailsMapper contactDetailsMapper;
    private final SemesterRepository semesterRepository;
    private final SubjectCardRepository subjectCardRepository;
    private final SubjectRepository subjectRepository;
    private final SubjectCardMapper subjectCardMapper;
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final GradeConverter converter;

    public StudentsServiceImpl(UserRepository userRepository, UserMapper userMapper, StudentMapper studentMapper,
                               ContactDetailsMapper contactDetailsMapper, SemesterRepository semesterRepository,
                               SubjectCardRepository subjectCardRepository, SubjectRepository subjectRepository,
                               SubjectCardMapper subjectCardMapper, GradeRepository gradeRepository,
                               GradeMapper gradeMapper, GradeConverter converter)
    {
        super(userRepository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.studentMapper = studentMapper;
        this.contactDetailsMapper = contactDetailsMapper;
        this.semesterRepository = semesterRepository;
        this.subjectCardRepository = subjectCardRepository;
        this.subjectRepository = subjectRepository;
        this.subjectCardMapper = subjectCardMapper;
        this.gradeRepository = gradeRepository;
        this.gradeMapper = gradeMapper;
        this.converter = converter;
    }


    @Override
    public List<GroupWithStudentsResponseDTO> getListOfGroupsWithStudents() {
        List<Semester> semestersWithGroupNames = semesterRepository
                .getAllBySemesterDate(new Date(System.currentTimeMillis()));

        return getGroupWithStudentsResponseDTOs(semestersWithGroupNames);
    }

    @Override
    public StudentDTO getStudentById() {
        UUID studentUuid = getUserFromContext().getId();

        return userRepository.findById(studentUuid)
                .map(user -> studentMapper.userToStudentDTO(
                        user, userMapper.userEntityToUserDto(user,
                                contactDetailsMapper.contactDetailsToUserContactDto(user.getContactDetails())),
                        getStudentSubjectCardResponseDTOs(user)
                )).orElseThrow();
    }

    @Override
    public List<StudentSubjectCardResponseDTO> getStudentSubjectCards(Integer semesterNumber) throws Exception {
        UUID studentUuid = getUserFromContext().getId();

        Optional<User> student = userRepository.getUserById(studentUuid);

        if (student.isEmpty())
            throw new Exception("There's no user with this id!");

        Optional<Semester> semester = semesterRepository
                .getSemesterBySemesterNumberAndUsersListContaining(semesterNumber, student.get());

        if (semester.isEmpty())
            throw new Exception("Semester not exists!");

        List<SubjectCard> subjectCards = subjectCardRepository.getAllByUserAndSemester(student.get(), semester.get());

        return subjectCards.stream().map(subjectCard -> subjectCardMapper.subjectCardToStudentSubjectCardResponseDTO(
                subjectCard, this.getGradeDTOs(subjectCard)
        )).collect(Collectors.toList());
    }

    @Override
    public List<StudentSubjectCardResponseDTO> getStudentSubjectCards(UUID subjectUuid) {
        UUID studentUuid = getUserFromContext().getId();

        Optional<User> student = userRepository.getUserById(studentUuid);

        if (student.isEmpty())
            throw new IllegalArgumentException("Student with this id does not exist!");

        Optional<Subject> subject = subjectRepository.getSubjectById(subjectUuid);

        if (subject.isEmpty())
            throw new IllegalArgumentException("Subject with this id does not exist!");

        List<SubjectCard> subjectCards = subjectCardRepository.getAllByUserAndSubject(student.get(), subject.get());

        return subjectCards.stream().map(subjectCard -> subjectCardMapper
                        .subjectCardToStudentSubjectCardResponseDTO(subjectCard, this.getGradeDTOs(subjectCard)))
                .collect(Collectors.toList());
    }


    @Override
    public GradeDTO updateStudentGrade(UUID studentUuid, UUID subjectCardUuid, UUID gradeUuid,
                                       GradeUpdateRequestDTO gradeDTO) throws Exception
    {

        if (userRepository.findById(studentUuid).isEmpty())
            throw new IllegalArgumentException("Student with this id does not exist!");

        if (subjectCardRepository.findById(subjectCardUuid).isEmpty())
            throw new IllegalArgumentException("SubjectCard with this id does not exist!");

        Optional<Grade> gradeOptional = gradeRepository.findById(gradeUuid);

        if (gradeOptional.isEmpty())
            throw new IllegalArgumentException("Grade with this id does not exist!");

        Grade grade = gradeOptional.get();

        Optional<Double> value = Optional.empty();

        try{
            value = converter.convertValueToType(gradeDTO.getGrade(), GradeType.valueOf(gradeDTO.getGradeType()));
        }catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        if (value.isEmpty())
            throw new Exception("Empty converted grade value!");

        grade.setValue(value.get());
        grade.setGradeType(GradeType.valueOf(gradeDTO.getGradeType()));
        grade.setWeight(Double.valueOf(gradeDTO.getGradeWeight()));
        grade.setDescription(gradeDTO.getGradeDescription());

        gradeRepository.save(grade);

        return gradeMapper.gradeToGradeDTO(grade);
    }

    @Override
    public GradeDTO createNewGrade(GradeCreateRequestDTO gradeCreateRequestDTO) throws Exception {

        Optional<User> user = userRepository.getUserById(gradeCreateRequestDTO.getStudentUuid());
        Optional<Subject> subject = subjectRepository.getSubjectById(gradeCreateRequestDTO.getSubjectUuid());

        if (user.isEmpty() || subject.isEmpty())
            throw new IllegalArgumentException();

        Optional<SubjectCard> subjectCard = subjectCardRepository.getSubjectCardByUserAndSubject(user.get(), subject.get());

        if (subjectCard.isEmpty())
            throw new Exception("Empty subject card!");

        Optional<Double> value = Optional.empty();

        try{
            value = converter.convertValueToType(gradeCreateRequestDTO.getGrade(),
                    GradeType.valueOf(gradeCreateRequestDTO.getGradeType()));

        }catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

        if (value.isEmpty())
            throw new Exception("Empty converted grade value!");

        Grade newGrade = Grade.builder().value(value.get())
                .gradeType(GradeType.valueOf(gradeCreateRequestDTO.getGradeType()))
                .weight(Double.valueOf(gradeCreateRequestDTO.getGradeWeight()))
                .description(gradeCreateRequestDTO.getGradeDescription())
                .subjectCard(subjectCard.get()).build();

        gradeRepository.save(newGrade);

        return gradeMapper.gradeToGradeDTO(newGrade);
    }

    private List<GroupWithStudentsResponseDTO> getGroupWithStudentsResponseDTOs(List<Semester> semesters) {
        return semesters.stream().map(semester -> studentMapper.semesterToGroupWithStudentsResponseDTO(semester,
                        this.getListOfStudentDTOByNewSemester(semester)))
                .collect(Collectors.toList());
    }

    private List<StudentDTO> getListOfStudentDTOByNewSemester(Semester semester) {
        return semester.getUsersList().stream().map(user -> {
            UserContactDTO contactDTO = contactDetailsMapper
                    .contactDetailsToUserContactDto(user.getContactDetails());

            return studentMapper.userToStudentDTO(user,
                    userMapper.userEntityToUserDto(user, contactDTO),
                    this.getStudentSubjectCardResponseDTOs(user));
        }).collect(Collectors.toList());
    }

    private List<StudentSubjectCardResponseDTO> getStudentSubjectCardResponseDTOs(User student) {
        return subjectCardRepository.getAllByUser(student).stream().map(subjectCard ->
                subjectCardMapper.subjectCardToStudentSubjectCardResponseDTO(subjectCard,
                        this.getGradeDTOs(subjectCard))
        ).collect(Collectors.toList());
    }

    private List<GradeDTO> getGradeDTOs(SubjectCard subjectCard) {
        return gradeRepository.getAllBySubjectCard(subjectCard).stream()
                .map(gradeMapper::gradeToGradeDTO).collect(Collectors.toList());
    }

}
