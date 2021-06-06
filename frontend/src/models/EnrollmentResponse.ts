import {Course} from 'src/models/Course';

export interface EnrollmentResponse {
  course: Course;
  enrollmentDate: string;
}
