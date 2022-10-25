/** Flagr
  * Flagr is a feature flagging, A/B testing and dynamic configuration microservice. The base path for all the APIs is \"/api/v1\".
  *
  * OpenAPI spec version: 1.1.13
  *
  * NOTE: This class is auto generated by the swagger code generator program.
  * https://github.com/swagger-api/swagger-codegen.git
  * Do not edit the class manually.
  */
package io.github.crystailx.scalaflagr.data

case class EvaluationEntity(
  entityID: Option[String] = None,
  entityType: Option[String] = None,
  entityContext: Option[Any] = None
)