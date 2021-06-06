import { MutationTree } from 'vuex';
import {Student} from 'src/models/Student';

const mutation: MutationTree<Student> = {
  setStudent (state: Student, payload: Student) {
    state = payload;
    console.log(state);
  }
};

export default mutation;
