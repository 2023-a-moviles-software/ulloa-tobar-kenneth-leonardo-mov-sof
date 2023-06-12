function setUpValue(name, val) {
    let element = document.querySelector(`[name="${name}"]`)
    element?.value = val
    if(element === null) {
        console.log(`No element matching ${name}=${val} have been found...`)
    }
}


/* TEMPLATE OBJECT */

let dataObj = {
    app_date: '2023-06-23',
    VisaTypeId: '', /* Las opciones de visado no se cargan directamente */
    first_name: 'MAYRA DEL CISNE',
    last_name: 'CARRIÓN TORO',
    dateOfBirth: '1981-10-07',
    phone: '983004005',
    nationalityId: '63',
    passportType: '01',
    passport_no: 'A8578415',
    pptIssueDate: '2023-01-30',
    pptExpiryDate: '2033-01-30',
    pptIssuePalace: 'QUITO'
}

console.log(Object.entries(dataObj));




