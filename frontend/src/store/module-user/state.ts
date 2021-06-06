import {User} from 'src/models/User';

function state(): User {
  return {
    id: 0,
    email: '',
    lastLogin: ''
  }
}

export default state;
