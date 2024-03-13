import requests
import datetime


def get_holiday_info(start_date, end_date):
    url = "https://timor.tech/api/holiday/info"
    holiday_info = {}

    current_date = datetime.datetime.strptime(start_date, "%Y-%m-%d")
    end_date = datetime.datetime.strptime(end_date, "%Y-%m-%d")

    while current_date <= end_date:
        date_str = current_date.strftime("%Y%m%d")
        params = {"date": date_str}
        response = requests.get(url, params=params)
        data = response.json()

        holiday_info[date_str] = {
            "date": current_date.strftime("%Y-%m-%d"),
            "is_workday": data.get("data") == 0,
            "is_holiday": data.get("data") == 1
        }

        current_date += datetime.timedelta(days=1)

    return holiday_info


if __name__ == "__main__":
    start_date = "2024-01-01"
    end_date = "2024-01-01"

    holiday_info = get_holiday_info(start_date, end_date)

    for date, info in holiday_info.items():
        print(f"Date: {info['date']}, Workday: {info['is_workday']}, Holiday: {info['is_holiday']}")