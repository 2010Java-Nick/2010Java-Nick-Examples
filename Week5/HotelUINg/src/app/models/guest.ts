import { Room } from './room';

export interface Guest {

    firstName : string;
    lastName : string;
    room? : Room;
    phoneNumber : string;
    roomNumber: number;

}