import {ActionTree} from 'vuex';
import {StateInterface} from '../index';
import {Student} from 'src/models/Student';
import {api} from 'boot/axios';

const actions: ActionTree<Student, StateInterface> = {
  fetchStudentData() {
    const localVarPath = '/students/user/' + String(1);
    return api.get(localVarPath);
  }
};

export default actions;
