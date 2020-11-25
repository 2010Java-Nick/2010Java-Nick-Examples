export class Guest {
    private name: string;
    private phoneNumber: string;
    private payment: number;

    constructor (name: string, phoneNumber: string, payment: number) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.payment = payment;
    }

    public getName(): string {
        return this.name;
    }

    public setName(name: string): void {
        this.name = name;
    }

    public getPhoneNumber(): string {
        return this.phoneNumber;
    }

    public setPhoneNumber(phoneNumber: string): void {
        this.phoneNumber = phoneNumber;
    }

    
    public getPayment(): number {
        return this.payment;
    }

    public setPayment(payment: number): void {
        this.payment = payment;
        let myNum = 75; //inferes type
        //myNum = "75";
        let myOtherNum; //does not infer type
        //The same as let myOtherNum: any; (bad practice)
        myOtherNum = 75;
        myOtherNum = "75";
    }

}