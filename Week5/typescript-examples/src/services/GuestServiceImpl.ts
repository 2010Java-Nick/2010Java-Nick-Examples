import { GuestService } from './GuestService';
import { Guest } from '../types/Guest';

export class GuestServiceImpl implements GuestService {

    public getGuest(): Guest {

        let name = document.getElementById("nameInput").value;
        let phoneNumber = document.getElementById("phoneNumberInput").value;
        let payment = document.getElementById("paymentInput").value;

        return new Guest(name, phoneNumber, payment);

    }

    constructor() {
        
    }

}