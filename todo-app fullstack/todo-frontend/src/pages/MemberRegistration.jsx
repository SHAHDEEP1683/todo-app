import React, { useState } from 'react';
import "../../public/css/register.css";
import axios from 'axios';

export default function Registration() {
  const [form, setForm] = useState({
    name: '',
    phoneNumber: '',
    email: '',
    password: ''
  });

  const [errors, setErrors] = useState({});

  const validate = () => {
    const newErrors = {};

    if (!form.name.trim()) {
      newErrors.name = 'Name is required';
    }

    if (!/^\d{10}$/.test(form.phoneNumber)) {
      newErrors.phoneNumber = 'Phone number must be 10 digits';
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email)) {
      newErrors.email = 'Invalid email address';
    }

    if (form.password.length < 6) {
      newErrors.password = 'Password must be at least 6 characters';
    } else if (!/\d/.test(form.password)) {
      newErrors.password = 'Password must contain at least one number';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setErrors({ ...errors, [e.target.name]: '' });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validate()) return;

    try {
      const response = await axios.post("http://localhost:8080/api/member/register", {
        name: form.name,
        phoneNumber: form.phoneNumber,
        authUsersRequestDto: {
          email: form.email,
          password: form.password
        }
      });

      console.log("Registration response:", response);
      alert("Registration successful!");

      if (response.status === 200) {
        localStorage.setItem("role", "MEMBER");
        window.location.href = "/member"; // Redirect
      }
    } catch (error) {
      console.error("Registration failed:", error);
      alert("Registration failed. Please try again.");
    }
  };

  return (
    <div className="registration-container">
      <form onSubmit={handleSubmit} className="registration-form shadow">
        <h2 className="text-center mb-4">Member Registration</h2>

        <div className="mb-3 row align-items-center">
          <label className="col-sm-4 col-form-label">Name</label>
          <div className="col-sm-8">
            <input
              type="text"
              name="name"
              className={`form-control ${errors.name ? 'is-invalid' : ''}`}
              value={form.name}
              onChange={handleChange}
            />
            {errors.name && <div className="invalid-feedback">{errors.name}</div>}
          </div>
        </div>

        <div className="mb-3 row align-items-center">
          <label className="col-sm-4 col-form-label">Phone Number</label>
          <div className="col-sm-8">
            <input
              type="text"
              name="phoneNumber"
              className={`form-control ${errors.phoneNumber ? 'is-invalid' : ''}`}
              value={form.phoneNumber}
              onChange={handleChange}
            />
            {errors.phoneNumber && <div className="invalid-feedback">{errors.phoneNumber}</div>}
          </div>
        </div>

        <div className="mb-3 row align-items-center">
          <label className="col-sm-4 col-form-label">Email</label>
          <div className="col-sm-8">
            <input
              type="email"
              name="email"
              className={`form-control ${errors.email ? 'is-invalid' : ''}`}
              value={form.email}
              onChange={handleChange}
            />
            {errors.email && <div className="invalid-feedback">{errors.email}</div>}
          </div>
        </div>

        <div className="mb-4 row align-items-center">
          <label className="col-sm-4 col-form-label">Password</label>
          <div className="col-sm-8">
            <input
              type="password"
              name="password"
              className={`form-control ${errors.password ? 'is-invalid' : ''}`}
              value={form.password}
              onChange={handleChange}
            />
            {errors.password && <div className="invalid-feedback">{errors.password}</div>}
          </div>
        </div>

        <button type="submit" className="btn btn-primary w-100">Register</button>
      </form>
    </div>
  );
}
