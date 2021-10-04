export function fetchFamilyMembersFromWS(success, failure, familyID) {
    fetch(`${process.env.REACT_APP_URL_API}/users?familyID=${familyID}`)
        .then((res) => res.json())
        .then((res) => success(res))
        .catch((err) => failure(err.message));
}

export function fetchFamiliesFromWS(success, failure) {
    fetch(`${process.env.REACT_APP_URL_API}/families`)
        .then((res) => res.json())
        .then((res) => success(res))
        .catch((err) => failure(err.message));
}

export function fetchPersonRelationsFromWS(success, failure, mainEmail) {
    fetch(`${process.env.REACT_APP_URL_API}/families/f53ce6a4-adad-4acc-a406-bea47002a4d0/relations/${mainEmail}`)
        .then((res) => res.json())
        .then((res) => success(res))
        .catch((err) => failure(err.message));
}