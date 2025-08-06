import React, { useState } from 'react';
import "../../public/css/login.css";
import axios from 'axios';

export default function Login() {
  const [form, setForm] = useState({
    email: '',
    password: ''
  });

  const [errors, setErrors] = useState({});

  const validate = () => {
    const newErrors = {};

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

    const endpoints = [
      { url: "http://localhost:8080/api/auth/superadmin", role: "SUPER_ADMIN" },
      { url: "http://localhost:8080/api/auth/admin", role: "ADMIN" },
      { url: "http://localhost:8080/api/auth/member", role: "MEMBER" }
    ];

    try {
      let success = false;

      for (const endpoint of endpoints) {
        try {
          const response = await axios.post(endpoint.url, {
            email: form.email,
            password: form.password
          });

          if (response.status === 200) {
            const { token, role } = response.data;

            localStorage.setItem("token", token);
            localStorage.setItem("role", role);

            alert("Login successful!");

            switch (role) {
              case "SUPER_ADMIN":
                window.location.href = "/superadmin";
                break;
              case "ADMIN":
                window.location.href = "/admin";
                break;
              case "MEMBER":
                window.location.href = "/member";
                break;
              default:
                window.location.href = "/";
            }

            success = true;
            break;
          }
        } catch (err) {
          // Continue trying next endpoint
        }
      }

      if (!success) {
        alert("Login failed. Invalid credentials.");
      }
    } catch (error) {
      console.error("Login failed:", error);
      alert("An error occurred during login.");
    }
  };

  return (
    <div className="Login-container">
      <form onSubmit={handleSubmit} className="Login-form shadow">
        <h2 className="text-center mb-4">Login</h2>

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

        <button type="submit" className="btn btn-primary w-100">Login</button>
      </form>
    </div>
  );
}
