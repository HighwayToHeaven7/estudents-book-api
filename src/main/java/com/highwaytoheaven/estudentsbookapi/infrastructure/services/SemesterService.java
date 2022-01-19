package com.highwaytoheaven.estudentsbookapi.infrastructure.services;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Major;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.Semester;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.User;
import com.highwaytoheaven.estudentsbookapi.infrastructure.repositories.SemesterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SemesterService {

    private final SemesterRepository semesterRepo;

    public Semester createNewSemesterByDateAndStudentUUID(User student, String groupName, Major major) throws Exception {
        int semesterNumber = 0;

        Optional<Integer> latestSemesterNumber = semesterRepo.getLatestSemesterNumberByUser(student);

        if (latestSemesterNumber.isPresent())
            semesterNumber = latestSemesterNumber.get();

        List<Date> dates = getSemesterStartAndEndDates();

        Semester semester = Semester.builder().semesterNumber(semesterNumber + 1).groupName(groupName)
                .semesterStartDate(dates.get(0)).semesterEndDate(dates.get(1)).major(major).build();

        semesterRepo.save(semester);

        return semester;
    }

    private List<Date> getSemesterStartAndEndDates() {
        int startMonthOfSummerSemester = 3, endMonthOfSummerSemester = 9, openingDayOfSummerSemester = 1,
                closingDayOfSummerSemester = 30;

        Date date = new Date(System.currentTimeMillis());
        String dateAsString = date.toString();
        String[] splitDate = dateAsString.split("-");

        Date startDate;
        Date endDate;

        if (Integer.parseInt(splitDate[1]) >= startMonthOfSummerSemester
                && Integer.parseInt(splitDate[2]) >= openingDayOfSummerSemester
                && Integer.parseInt(splitDate[1]) <= endMonthOfSummerSemester
                && Integer.parseInt(splitDate[2]) <= closingDayOfSummerSemester)
        {
            startDate = Date.valueOf(splitDate[0] + "-03-01");
            endDate = Date.valueOf(splitDate[0] + "-09-30");
        }
        else {
            if(Integer.parseInt(splitDate[1] ) < 3) {
                startDate = Date.valueOf((Integer.parseInt(splitDate[0])-1) + "-10-01");
                endDate = Date.valueOf(splitDate[0] + "-02-28");
            }
            else {
                startDate = Date.valueOf(splitDate[0] + "-10-01");
                endDate = Date.valueOf((Integer.parseInt(splitDate[0])+1) + "-02-28");
            }
        }

        return List.of(startDate, endDate);
    }
}
