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