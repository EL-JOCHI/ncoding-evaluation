import { Module } from 'vuex';
import { StateInterface } from '../index';
import state from './state';
import {Student} from 'src/models/Student';
import actions from './actions';
import getters from './getters';
import mutations from './mutations';

const studentModule: Module<Student, StateInterface> = {
  namespaced: true,
  actions,
  getters,
  mutations,
  state
};

export default studentModule;
