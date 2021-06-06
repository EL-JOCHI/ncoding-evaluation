export interface EnrollmentKey {
  studentId: number;
  courseId: number;
}

export interface Enrollment {
  id?: EnrollmentKey;
  enrollmentDate?: string;
}
