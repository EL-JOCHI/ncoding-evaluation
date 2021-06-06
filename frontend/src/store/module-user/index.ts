import { Module } from 'vuex';
import { StateInterface } from '../index';
import actions from './actions';
import getters from './getters';
import mutations from './mutations';
import state from './state';
import {User} from 'src/models/User';

const userModule: Module<User, StateInterface> = {
  namespaced: true,
  actions,
  getters,
  mutations,
  state
};

export default userModule;
