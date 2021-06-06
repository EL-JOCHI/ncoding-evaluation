import { GetterTree } from 'vuex';
import { StateInterface } from '../index';
import {Student} from 'src/models/Student';

const getters: GetterTree<Student, StateInterface> = {
  someAction (/* context */) {
    // your code
  }
};

export default getters;
