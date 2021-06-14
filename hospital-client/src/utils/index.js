import React from 'react'
import { ErrorMessage } from 'formik';
import { moment } from 'moment';

export function withFormikField(Component) {
    return ({ field, form, customErrorMessageValues = {}, ...props }) => (
        <Component
            error={!!(form.touched[field.name] && form.errors[field.name])}
            {...field}
            {...props}
            helperText={
                <ErrorMessage name={field.name}>
                    {message =>
                        message
                    }
                </ErrorMessage>
            }
        />
    );
}

export function formatDate(prevDate) {

    const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

    const date = new Date(prevDate);
    const month = months[date.getMonth()].slice(0, 3);

    const dateNow = new Date();

    if ((dateNow - date) / 60 / 1000 < 30)
        return 'scraping...';


    return `${date.getHours()}:${date.getMinutes()}, ${date.getDate()} ${month} ${date.getFullYear()}`;
}

export const months = { 
    1: "January", 
    2: "February", 
    3: "March", 
    4: "April",
    5: "May",
    6: "June",
    7: "July",
    8: "August",
    9: "September",
    10: "October",
    11: "November",
    12: "December"
}

export const formatTimestamp = timestamp => {
    const arr = timestamp.split("T");
    const date = arr[0].split("-");
    const datestr = date[2] + "." + date[1] + "." + date[0] + ".";
 
    return datestr
 }

 export const formatTimestampWithTime = timestamp => {
    const datetime = timestamp.split("T");
    const date = datetime[0].split("-");
    const time = datetime[1].slice(0, 5);
    const datestr = date[2] + "." + date[1] + "." + date[0] + "." + " " + time;

    return datestr
 }


